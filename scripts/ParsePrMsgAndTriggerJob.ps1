####################################
# Parse PR Message and Trigger Job #
# Author: Adin Miller              #
####################################

# Checks the correct number of arguments were given
if ($Args.Count -lt 1) {
  Write-Host "Usage: .\ParsePrMsgAndTriggerJob.ps1 <PR-message>"
  Write-Host "Parses the given PR message, which should contain arugments to pass along to a job"
  Write-Host "Example: .\ParsePrMsgAndTriggerJob.ps1 'Made changes EXECUTE AUTOMATION group=test;env=qa;branch=qa'"
  Write-Host ""

  exit 1
}

# Obtains PR message argument
$fullMsg = [string] $Args[0]
$separator = [string[]]@("EXECUTE AUTOMATION")
$splitOpt = [System.StringSplitOptions]::RemoveEmptyEntries
$argStr = $fullMsg.Split($separator, $splitOpt)[1].trim()

# If no arguments were specified in the PR message
if ($argStr.Length -eq 0) {
  Write-Host "No arguments were specified in the PR message; failing script"
  exit 1
}

$jobArgs = $argStr.Split(";")

# A list of all possible job arguments
# REQUIRED
$groupArg = ""
$envArg = ""
$branchArg = ""
# NOT REQUIRED - must have default value
$browserArg = "Chrome"

# For each given job argument
foreach ($jobArg in $jobArgs) {
  $argPieces = $jobArg.Split("=")
  $argName = $argPieces[0]
  $argValue = $argPieces[1]

  Switch -Exact ($argName) {
    "group" {
      $groupArg = $argValue
    }
    "env" {
      $envArg = $argValue
    }
    "branch" {
      $branchArg = $argValue
    }
    "browser" {
      $browserArg = $argValue
    }
    default {
      Write-Host "$argName argument not recognized"
    }
  }
}

# Checks that required arguments were set
$argsSetFlag = $groupArg -ne "" -and $envArg -ne "" -and $branchArg -ne ""
if (!$argsSetFlag) {
  Write-Host "Not all required arguments were set: group=$groupArg; env=$envArg; branch=$branchArg"
  exit 1
}

# TODO call endpoint to run job with given argumemts
Write-Host("-Dtest.group=$groupArg -Dtest.env=$envArg -Dtest.branch=$branchArg -Dtest.browser=$browserArg")