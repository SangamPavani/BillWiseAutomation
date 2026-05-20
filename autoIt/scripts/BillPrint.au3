#comments-start
#region --- Au3Recorder generated code Start (v3.3.9.5 KeyboardLayout=00000409)  ---

#region --- Internal functions Au3Recorder Start ---
Func _Au3RecordSetup()
Opt('WinWaitDelay',100)
Opt('WinDetectHiddenText',1)
Opt('MouseCoordMode',0)
Local $aResult = DllCall('User32.dll', 'int', 'GetKeyboardLayoutNameW', 'wstr', '')
If $aResult[1] <> '00000409' Then
  MsgBox(64, 'Warning', 'Recording has been done under a different Keyboard layout' & @CRLF & '(00000409->' & $aResult[1] & ')')
EndIf

EndFunc
#comments-end


#comments-start
Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
EndFunc
#comments-end

#comments-start
_AU3RecordSetup()
#endregion --- Internal functions Au3Recorder End ---
#comments-end

#comments-start

_WinWaitActivate("Save Print Output As","")
Send(@WorkingDir&"\autoIt\ExportFiles\BillPrint.pdf{ENTER}")
#comments-end




WinWait("Save Print Output As", "", 5)
ControlFocus("Save Print Output As", "", "Edit1")
Send(@WorkingDir&"\autoIt\ExportFiles\BillPrint")
Sleep(2000)
ControlClick("Save Print Output As", "", "Button2")



#comments-start
#endregion --- Au3Recorder generated code End ---
#comments-end