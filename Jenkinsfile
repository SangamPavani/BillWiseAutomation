pipeline {

    agent any
    
     options {

        disableConcurrentBuilds()

    }

    tools {

        jdk 'JDK'
        maven 'Maven'

    }

    triggers {

        cron('H 19 * * *')

    }

    environment {

        PATCH_FOLDER = 'G:/Patches'
        PROJECT_PATH = 'F:/FocusXBillWise1/BillWise'

    }

    stages {

        stage('Clean Old Reports') {

            steps {

                dir("${PROJECT_PATH}") {

                    bat '''
                    if exist test-output rmdir /s /q test-output
                    if exist target rmdir /s /q target
                    '''

                }
            }
        }

      stage('Check Patch Folder') {

    steps {

        script {

            def patchExists = bat(

                script: '''
                @echo off

                dir G:\\Patches\\*.exe /b | findstr /v "PatchHandler.exe" >nul

                if %errorlevel%==0 (
                    exit /b 0
                ) else (
                    exit /b 1
                )
                ''',

                returnStatus: true

            )

            if (patchExists != 0) {

                currentBuild.result = 'NOT_BUILT'

                error "No patch found"

            }
        }
    }
}

        stage('Clear Temp Cache') {

            steps {

                bat '''

                echo =========================
                echo CLEARING TEMP CACHE
                echo =========================

                del /s /f /q "%TEMP%\\*.*" 2>nul

                for /d %%x in ("%TEMP%\\*") do (
                    rd /s /q "%%x" 2>nul
                )

                del /s /f /q "%TMP%\\*.*" 2>nul

                for /d %%x in ("%TMP%\\*") do (
                    rd /s /q "%%x" 2>nul
                )

                echo TEMP Cache Cleared

                '''
            }
        }
        
       stage('Stop IIS') {
steps {
script {


        def status = bat(
            script: 'iisreset /stop',
            returnStatus: true
        )

        if(status == 0){
            echo "IIS stopped successfully"
        } else {
            error("Failed to stop IIS")
        }
    }
}


}
/*

      stage('Install Latest Patch') {

    steps {

        script {

            def latestPatch = bat(

                script: '''
                @echo off

                for /f "delims=" %%f in ('dir G:\\Patches\\*.exe /b /o-d /t:c ^| findstr /v "PatchHandler.exe"') do (
                    echo %%f
                    goto :done
                )

                :done
                ''',

                returnStdout: true

            ).trim()

            env.PATCH_NAME = latestPatch

            bat """

            cd /d G:\\Patches

            echo =========================
            echo INSTALLING LATEST PATCH
            echo =========================

            echo Executing Patch : ${env.PATCH_NAME}

           // start /wait "" "${env.PATCH_NAME}"
           start "" "${env.PATCH_NAME}"
            
         /*   // Wait for popup to appear 
            sleep(time: 20, unit: 'SECONDS') 
            def robot = new java.awt.Robot() 
            // Press ENTER for YES popup 
             robot.keyPress(java.awt.event.KeyEvent.VK_ENTER)
             robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER) 
             // Wait for installation 
             sleep(time: 30, unit: 'SECONDS') 
             // Press ENTER again to close completion popup 
             robot.keyPress(java.awt.event.KeyEvent.VK_ENTER) 
             robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER)
*/
	/*		timeout /t 20 
			echo Set WshShell = CreateObject("WScript.Shell") > press_enter.vbs 
			echo WScript.Sleep 3000 >> press_enter.vbs 
			echo WshShell.SendKeys "{ENTER}" >> press_enter.vbs 
			cscript //nologo press_enter.vbs
            echo PATCH INSTALLATION COMPLETED

            """
        }
    }
}
*/

stage('Install Latest Patch') {

    steps {

        script {

            def latestPatch = bat(
                script: '''
                @echo off

                for /f "delims=" %%f in ('dir G:\\Patches\\*.exe /b /o-d /t:c ^| findstr /v "PatchHandler.exe"') do (
                    echo %%f
                    goto :done
                )

                :done
                ''',
                returnStdout: true
            ).trim()

            env.PATCH_NAME = latestPatch

           bat '''
cd /d G:/Patches

echo =========================
echo INSTALLING LATEST PATCH
echo =========================

echo Executing Patch : %PATCH_NAME%

start "" "%PATCH_NAME%"

 echo Waiting for popup...

powershell -ExecutionPolicy Bypass -Command ^
"$wshell = New-Object -ComObject WScript.Shell; ^
do { ^
    Start-Sleep -Seconds 2; ^
    $found = $wshell.AppActivate('FocusX Web Patch'); ^
} until ($found); ^
Start-Sleep -Seconds 2; ^
$wshell.SendKeys('{TAB}'); ^
Start-Sleep -Seconds 1; ^
$wshell.SendKeys('{ENTER}'); ^
Write-Host 'Yes button clicked'; ^
Start-Sleep -Seconds 30"


echo PATCH INSTALLATION COMPLETED
'''
}

    }
}

       stage('Start IIS') {

    steps {

        bat '''

        echo =========================
        echo STARTING IIS
        echo =========================

        iisreset /start

        echo IIS STARTED SUCCESSFULLY

        '''
    }
}
        stage('Execute Automation') {

            steps {

                dir("${PROJECT_PATH}") {

                    bat '''

                    mvn clean test -Dsurefire.suiteXmlFiles=testng.xml

                    '''

                }
            }
        }

        stage('Move Installed Patch') {

    steps {

        bat """

        if not exist "G:\\Patches\\Completed" (
            mkdir "G:\\Patches\\Completed"
        )

        move "G:\\Patches\\${PATCH_NAME}" "G:\\Patches\\Completed\\"

        """

    }
}

    }

    post {

        always {

            dir("${PROJECT_PATH}") {

                publishHTML([

                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'test-output',
                    reportFiles: 'index.html',
                    reportName: 'Automation Report'

                ])

                archiveArtifacts artifacts: 'Screenshots/*.png',
                allowEmptyArchive: true

            }
        }
    }
}