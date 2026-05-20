
Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
EndFunc

_WinWaitActivate("Focus-Master Authorization - Google Chrome","")
MouseMove(648,314)
MouseDown("left")
MouseMove(953,308)
MouseUp("left")
MouseMove(655,334)
MouseDown("left")
MouseMove(999,336)
MouseUp("left")
MouseMove(674,351)
MouseDown("left")
MouseMove(1156,304)
MouseUp("left")
MouseClick("left",943,257,1)
MouseClick("left",908,262,1)

#endregion --- Au3Recorder generated code End ---