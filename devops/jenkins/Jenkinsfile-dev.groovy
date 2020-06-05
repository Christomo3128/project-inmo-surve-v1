@Library('cix-shared-library')
import sharedlib.JenkinsfileUtil

def utils = new JenkinsfileUtil(steps, this)

def deploymentEnviroment = "dev"
def projectName = 'inmo'
def appName = 'channel-inmo-survey-v1'
def spAppId = 'SVPRAUTINMODES01'
def resourceGroupName = 'rsgreu2inmod01'
def aksName = 'aksveu2inmod01'

node('master')
{
    stage('Preparation')
    {
        env.deploymentEnviroment = "${deploymentEnviroment}"
        env.projectName = "${projectName}"
        env.appName = "${appName}"

        utils.prepare()
    }

    stage('SCM')
    {
        checkout scm
    }

    stage('Create Dockerfile')
    {
        utils.createDockerfile()
    }

    stage('Replace Secret Content')
    {
        //1st param: credential Id, 2nd param: word to replace in yml file, 3rd param: environment
        utils.replaceSecretContent("INMO_BD_USER", "INMO_BD_USER")
        utils.replaceSecretContent("INMO_BD_PASS", "INMO_BD_PASS")
        utils.replaceSecretContent("INMO_KEYVAULT_SP_CLIENT_ID", "INMO_KEYVAULT_SP_CLIENT_ID")
        utils.replaceSecretContent("INMO_KEYVAULT_SP_CLIENT_SECRET", "INMO_KEYVAULT_SP_CLIENT_SECRET")
        utils.replaceSecretContent("INMO_GOOGLE_RECAPTCHA_KEY_SECRET", "INMO_GOOGLE_RECAPTCHA_KEY_SECRET")
        utils.replaceSecretContent("INMO_JWT_SECRET_KEY", "INMO_JWT_SECRET_KEY")
    }

    stage('Build')
    {
        utils.buildMavenApp()
    }

    stage('SonarQube Analysis')
    {
        utils.executeSonarForMaven()
    }

    stage('SonarQube Quality Validation')
    {
        utils.validateSonarQualityGate()
    }

    stage('Docker Build')
    {
        utils.buildDockerImage()
    }

    stage('Deploy')
    {
        utils.deployImageToAKS("${spAppId}", "${resourceGroupName}", "${aksName}", "${appName}", "${projectName}")
    }

}
