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

Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
EndFunc

#comments-start
_AU3RecordSetup()
#endregion --- Internal functions Au3Recorder End ---
#comments-end
_WinWaitActivate("Focus - Google Chrome","")
MouseClick("left",1163,441,1)

_WinWaitActivate("Focus - Google Chrome","")
MouseMove(1163,441)
MouseDown("left")
MouseMove(768,327)
MouseUp("left")
MouseClick("left",768,328,1)

_WinWaitActivate("Focus - Google Chrome","")
MouseMove(811,381)
MouseDown("left")
MouseMove(1014,633)
MouseUp("left")
MouseClick("left",1014,633,1)

#comments-start
#endregion --- Au3Recorder generated code End ---
#comments-end
