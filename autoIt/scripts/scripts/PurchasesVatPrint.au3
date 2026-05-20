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

#comments-start


_WinWaitActivate("Focus-Purchase Voucher VAT - Google Chrome","")
#comments-end
MouseClick("left",1099,291,1)
Sleep(2000)
MouseDown("left")
Sleep(2000)
MouseMove(272,379)
Sleep(2000)
MouseUp("left")
Sleep(2000)
MouseClick("left",1075,310,1)
Sleep(2000)
MouseClick("left",1096,330,1)
Sleep(2000)
MouseDown("left")
Sleep(2000)
MouseMove(598,375)
Sleep(2000)
MouseUp("left")
Sleep(2000)
MouseClick("left",1077,310,1)
Sleep(2000)
#comments-start


_WinWaitActivate("Focus-Purchase Voucher VAT - Google Chrome","")
#comments-end
MouseClick("left",1078,354,1)
Sleep(2000)
MouseClick("left",1100,374,1)
Sleep(2000)
MouseMove(1104,371)
Sleep(2000)
MouseDown("left")
Sleep(2000)
MouseMove(277,418)
Sleep(2000)
MouseUp("left")
Sleep(2000)
MouseClick("left",1077,353,1)
Sleep(2000)
MouseClick("left",1107,436,1)
Sleep(2000)
MouseDown("left")
Sleep(2000)
MouseMove(586,414)
Sleep(2000)
MouseUp("left")
Sleep(2000)
#comments-start


_WinWaitActivate("Focus-Purchase Voucher VAT - Google Chrome","")
#comments-end
MouseClick("left",1074,476,1)
Sleep(2000)
MouseMove(1103,497)
Sleep(2000)
MouseDown("left")
Sleep(2000)
MouseMove(273,452)
Sleep(2000)
MouseUp("left")
Sleep(2000)
MouseClick("left",1075,479,1)
Sleep(2000)
MouseClick("left",1104,541,1)
Sleep(2000)
MouseDown("left")
Sleep(2000)
MouseMove(588,452)
Sleep(2000)
MouseUp("left")
Sleep(2000)
#comments-start


_WinWaitActivate("Focus-Purchase Voucher VAT - Google Chrome","")
#comments-end
MouseClick("left",1075,519,1)
Sleep(2000)
MouseClick("left",1078,541,1)
Sleep(2000)
MouseMove(1101,561)
Sleep(2000)
MouseDown("left")
Sleep(2000)
MouseMove(277,490)
Sleep(2000)
MouseUp("left")
Sleep(2000)
MouseClick("left",1077,540,1)
#comments-start
#endregion --- Au3Recorder generated code End ---
#comments-end