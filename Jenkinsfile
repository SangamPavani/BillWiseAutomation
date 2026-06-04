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
        
      /* stage('Stop IIS') {
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


}*/


stage('Stop Pronghorn Service') {
    steps {
        script {

            def status = bat(
                script: 'net stop "PronghornService"',
                returnStatus: true
            )

            if (status == 0) {
                echo "Pronghorn Service stopped successfully"
            } else {
                error("Failed to stop Pronghorn Service")
            }
        }
    }
}


stage('Stop IIS') {
    steps {
        script {

            int maxRetries = 2
            int attempt = 0
            boolean stopped = false

            while (attempt < maxRetries && !stopped) {

                attempt++

                echo "Stopping IIS - Attempt ${attempt}"

                def status = bat(
                    script: 'iisreset /stop',
                    returnStatus: true
                )

                if (status == 0) {
                    stopped = true
                    echo "IIS stopped successfully"
                } else {
                    echo "IIS stop failed. Waiting 10 seconds before retry..."
                    sleep(time: 10, unit: 'SECONDS')
                }
            }

            if (!stopped) {
                error("Unable to stop IIS after ${maxRetries} attempts")
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

	Start-Sleep -Seconds 200; ^
    $wshell.AppActivate('FocusX Web Patch'); ^
    Start-Sleep -Milliseconds 200; ^
    $wshell.SendKeys('%y'); ^
    Write-Host 'Yes Sent';^

	Start-Sleep -Seconds 5;^

	$wshell.SendKeys('{ENTER}');^

	Write-Host 'Enter Sent';"
	
	echo Waiting for patch installation to complete...

:waitPatch

tasklist | findstr /i "FocusX Update.exe" >nul

if not errorlevel 1 (
    echo Patch still running...
   ping 127.0.0.1 -n 11 >nul
    goto waitPatch
)

echo Patch installation completed
    
echo PATCH INSTALLATION COMPLETED
'''
}

    }
}


/*       stage('Start IIS') {

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
       */
       
       stage('Start IIS') {
    steps {
        script {

            int maxRetries = 2
            int attempt = 0
            boolean started = false

            while (attempt < maxRetries && !started) {

                attempt++

                echo "Starting IIS - Attempt ${attempt}"

                def status = bat(
                    script: 'iisreset /start',
                    returnStatus: true
                )

                if (status == 0) {
                    started = true
                    echo "IIS started successfully"
                } else {
                    echo "IIS start failed. Waiting 10 seconds before retry..."
                    sleep(time: 10, unit: 'SECONDS')
                }
            }

            if (!started) {
                error("Unable to start IIS after ${maxRetries} attempts")
            }
        }
    }
}


/*stage('Restart IIS and Pronghorn Service') {

    steps {

        script {

            echo "========================="
            echo "RESTARTING PRONGHORN SERVICE"
            echo "========================="

            bat 'net stop "PronghornService"'
            sleep(time: 10, unit: 'SECONDS')

            def pronghornStatus = bat(
                script: 'net start "PronghornService"',
                returnStatus: true
            )

            if (pronghornStatus != 0) {
                error("Failed to start Pronghorn Service")
            }

            echo "Pronghorn Service restarted successfully"
            
             echo "========================="
            echo "RESTARTING IIS"
            echo "========================="

            def iisStatus = bat(
                script: 'iisreset /restart',
                returnStatus: true
            )

            if (iisStatus != 0) {
                error("Failed to restart IIS")
            }

            echo "IIS restarted successfully"

            echo "Waiting for application startup..."
            sleep(time: 60, unit: 'SECONDS')
        }
    }
}
*/

stage('Restart Pronghorn Service') {
    steps {
        bat '''
        echo =========================
        echo RESTARTING PRONGHORN
        echo =========================

        sc stop PronghornService

        ping 127.0.0.1 -n 15 >nul

        sc start PronghornService

        echo PRONGHORN RESTARTED
        '''
    }
}

stage('Restart IIS') {
    steps {
        script {

            def stopStatus = bat(
                script: '''
                @echo off

                iisreset /stop

                if %errorlevel% neq 0 (
                    echo Retry IIS Stop...
                    ping 127.0.0.1 -n 10 >nul
                    iisreset /stop
                )

                exit /b %errorlevel%
                ''',
                returnStatus: true
            )

            if (stopStatus != 0) {
                error("Failed to stop IIS")
            }

            def startStatus = bat(
                script: '''
                @echo off

                iisreset /start

                if %errorlevel% neq 0 (
                    echo Retry IIS Start...
                    ping 127.0.0.1 -n 10 >nul
                    iisreset /start
                )

                exit /b %errorlevel%
                ''',
                returnStatus: true
            )

            if (startStatus != 0) {
                error("Failed to start IIS")
            }

            echo "IIS restarted successfully"
        }
    }
}

stage('Verify Application') {
    steps {
        bat '''
        sc query PronghornService
        iisreset /status
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