'-------------------------------------------------------------------------------------------------
'Causes the computer to shutdown automatically
'-------------------------------------------------------------------------------------------------
'Set OpSysSet = GetObject("winmgmts:{(Shutdown)}//./root/cimv2").ExecQuery("select * from Win32_OperatingSystem where Primary=true")

'for each OpSys in OpSysSet
'    OpSys.Reboot()
'next






'-------------------------------------------------------------------
'Causes the computer to display the shutdown prompt
'-------------------------------------------------------------------
'set WshShell = wscript.CreateObject("Shell.Application")
'WshShell.ShutdownWindows





beep("7")
    '#--------------------------------------------------------------------------
    '#  FUNCTION.......:  beep()
    '#  ARGUMENTS......:  iTimes = the number of times the computer will beep.
    '#  PURPOSE........:  Causes the computer's internal speaker to beep. On 
    '#                    some systems the beep will be executed from the actual
    '#                    speakers.
    '#  EXAMPLE........:  beep("7")
    '#  NOTES..........:  This was surprisingly hard to figure out, yet highly
    '#                    useful. There is a timing issue, the script will
    '#                    execute the beeps faster than the speaker can make
    '#                    individual noises.
    '#--------------------------------------------------------------------------
    Function beep(iTimes)
        Set oShell = CreateObject("Wscript.Shell")
        Dim iTemp
        For iTemp = 1 To iTimes
            oShell.Run "%comspec% /c echo " & Chr(7), 0, False
            Wscript.Sleep 300
        Next
    End Function
