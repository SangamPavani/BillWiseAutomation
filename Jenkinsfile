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
                     if exist allure-results rmdir /s /q allure-results
            		 if exist allure-report rmdir /s /q allure-report
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

        REM User Temp
        del /s /f /q "%TEMP%\\*.*" 2>nul
        for /d %%x in ("%TEMP%\\*") do (
            rd /s /q "%%x" 2>nul
        )

        REM Windows Temp
        del /s /f /q "C:\\Windows\\Temp\\*.*" 2>nul
        for /d %%x in ("C:\\Windows\\Temp\\*") do (
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


/*stage('Stop Pronghorn Service') {
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
}*/


/*
stage('Stop Pronghorn Service') {
    steps {
        bat '''
        echo Stopping Pronghorn Service...

        net stop "PronghornService"

        sc query "PronghornService"

        timeout /t 10 /nobreak >nul

        sc query "PronghornService" | find "STOPPED" >nul

        if errorlevel 1 (
            echo Service did not stop. Killing process...

            for /f "tokens=2 delims=: " %%a in ('sc queryex "PronghornService" ^| find "PID"') do (
                taskkill /F /PID %%a
            )
        )

        echo Pronghorn Service Stopped
        '''
    }
}
*/

stage('Stop Pronghorn Service') {
    steps {
        bat(returnStatus: true, script: '''
        echo Checking Pronghorn Service...

        sc query "PronghornService" >nul 2>&1

        if errorlevel 1 (
            echo PronghornService is not installed. Skipping stop operation.
            exit /b 0
        )

        echo Stopping Pronghorn Service...

        net stop "PronghornService"

        timeout /t 10 /nobreak >nul

        echo Pronghorn Service Stopped
        ''')
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
/*
stage('Install Latest Patch') {

    steps {

        script {

           def latestPatch = bat(
    script: '''
    @echo off
    for /f "delims=" %%f in ('dir /b /o-d G:\\Patches\\*.exe') do (
        echo %%f
        goto :done
    )
    :done
    ''',
    returnStdout: true
).trim()

echo "Latest Patch Found = [${latestPatch}]"

env.PATCH_NAME = latestPatch

           /* env.PATCH_NAME = latestPatch*/
       /*   if (!latestPatch?.trim()) {
    error "No patch found in G://Patches"
}

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

echo Last ErrorLevel = %ERRORLEVEL%

echo PATCH INSTALLATION COMPLETED

exit /b 0
    
echo PATCH INSTALLATION COMPLETED
'''
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
                for /f "delims=" %%f in ('dir /b /o-d G:\\Patches\\*.exe') do (
                    echo %%f
                    goto :done
                )
                :done
                ''',
                returnStdout: true
            ).trim()

            echo "Latest Patch Found = [${latestPatch}]"

            env.PATCH_NAME = latestPatch

            if (!latestPatch?.trim()) {
                error "No patch found in G://Patches"
            }

            bat '''
            cd /d G:/Patches

            echo =========================
            echo INSTALLING LATEST PATCH
            echo =========================

            echo Executing Patch : %PATCH_NAME%

            start "" "%PATCH_NAME%"

            echo Waiting for patch window...

powershell -ExecutionPolicy Bypass -Command "$ws=New-Object -ComObject WScript.Shell; while(-not $ws.AppActivate('FocusX Web Patch')){Start-Sleep 2}; Start-Sleep 2; Add-Type 'using System; using System.Runtime.InteropServices; public class Win{[DllImport(/"user32.dll/")] public static extern bool ShowWindowAsync(System.IntPtr hWnd,int nCmdShow);[DllImport(/"user32.dll/")] public static extern System.IntPtr FindWindow(string a,string b);}'; $h=[Win]::FindWindow($null,'FocusX Web Patch'); if($h -ne 0){[Win]::ShowWindowAsync($h,9); Start-Sleep 500; [Win]::ShowWindowAsync($h,3)}"




            echo Waiting for patch installation to complete...

            :waitPatch

            tasklist | findstr /i "FocusX Update.exe" >nul

            if not errorlevel 1 (
                echo Patch still running...
                ping 127.0.0.1 -n 11 >nul
                goto waitPatch
            )

            echo PATCH INSTALLATION COMPLETED

            exit /b 0
            '''
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
                for /f "delims=" %%f in ('dir /b /o-d G:\\Patches\\*.exe') do (
                    echo %%f
                    goto :done
                )
                :done
                ''',
                returnStdout: true
            ).trim()

            echo "Latest Patch Found = [${latestPatch}]"

            env.PATCH_NAME = latestPatch

            if (!latestPatch?.trim()) {
                error "No patch found in G:\\Patches"
            }

            bat '''
            @echo off

            cd /d G:\\Patches

            echo =========================
            echo INSTALLING LATEST PATCH
            echo =========================

            echo Executing Patch : %PATCH_NAME%

            start "" "%PATCH_NAME%"
            
            powershell -ExecutionPolicy Bypass -Command ^
"$wshell = New-Object -ComObject WScript.Shell; ^
do { ^
    Start-Sleep -Seconds 2; ^
    $found = $wshell.AppActivate('FocusX Web Patch'); ^
} until ($found); ^
Write-Host 'Popup Found'; ^
Start-Sleep -Seconds 1; ^
$wshell.SendKeys('{LEFT}'); ^
Start-Sleep -Milliseconds 500; ^
$wshell.SendKeys('{ENTER}');"
            echo PATCH INSTALLATION COMPLETED

            exit /b 0
            '''
        }
    }
}
*/
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
       
       
 /*      stage('Start IIS') {
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
*/

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
/*
stage('Restart Pronghorn Service') {
    steps {
        bat '''
        echo =========================
        echo RESTARTING PRONGHORN
        echo =========================

        sc stop PronghornService

        ping 127.0.0.1 -n 15 >nul

       sc start PronghornService

:waitPronghorn

sc query PronghornService | find "RUNNING" >nul

if errorlevel 1 (
    echo Waiting for Pronghorn to become RUNNING...
    ping 127.0.0.1 -n 10 >nul
    goto waitPronghorn
)

echo Pronghorn is RUNNING
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
*/
stage('Verify Application') {
    steps {
        bat '''
        sc query PronghornService
        iisreset /status
        '''
    }
}

stage('Wait For Application Startup') {
    steps {
        echo "Waiting for application startup..."
         sleep(time: 1, unit: 'MINUTES')
    }
}

/*
stage('Final Restart Pronghorn') {
    steps {
        bat '''
        sc stop PronghornService

ping 127.0.0.1 -n 30 >nul

sc start PronghornService

:waitPronghorn

sc query PronghornService | find "RUNNING" >nul

if errorlevel 1 (
    echo Waiting for Pronghorn to become RUNNING...
    ping 127.0.0.1 -n 10 >nul
    goto waitPronghorn
)

echo Pronghorn is RUNNING
        '''
    }
}

stage('Final Restart IIS') {
    steps {
        bat '''
        iisreset /restart
        
        :waitIIS

iisreset /status | find "Running" >nul

if errorlevel 1 (
    echo Waiting for IIS...
    ping 127.0.0.1 -n 10 >nul
    goto waitIIS
)

echo IIS is RUNNING
        '''
    }
}

stage('Wait After Final Restart') {
    steps {
        echo "Waiting after final restart..."
        sleep(time: 2, unit: 'MINUTES')
    }
}*/
       stage('Execute Automation') {
    steps {
        dir("${PROJECT_PATH}") {
            catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml'
            }
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
			
			allure([
    includeProperties: false,
    jdk: '',
    results: [[path: 'target/allure-results']]
])

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports/Suite',
                reportFiles: '*.html',
                reportName: 'Automation Report'
            ])
            
            archiveArtifacts artifacts: 'reports/**/*.*',
            allowEmptyArchive: true
        }
        
        emailext(
            subject: "Jenkins Build #${BUILD_NUMBER} - ${currentBuild.currentResult}",
            body: """
            Build Status: ${currentBuild.currentResult}

            Job Name: ${JOB_NAME}
            Build Number: ${BUILD_NUMBER}

            Build URL:
            ${BUILD_URL}

            HTML Report:
            ${BUILD_URL}Automation_20Report/

            Allure Report:
            ${BUILD_URL}allure/

            """,
            to: 'emailvalidationone@gmail.com'
        )
    

    }
}
}