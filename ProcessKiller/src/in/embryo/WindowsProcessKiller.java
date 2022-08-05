package in.embryo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowsProcessKiller {

	// command used to get list of running task
	private static final String TASKLIST = "tasklist";
	// command used to kill a task
	private static final String KILL = "taskkill /f /IM ";
	
	private static Boolean result = false;

	public boolean isProcessRunning(String serviceName) {

		try {
			Process pro = Runtime.getRuntime().exec(TASKLIST);
			BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				if (line.startsWith(serviceName)) {
					System.out.println("*****" + line);
					result = true;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void killProcess(String serviceName) {

		try {
			Runtime.getRuntime().exec(KILL + serviceName);
			System.out.println(serviceName + " killed successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//added comemt
	public static void main(String[] args) {

		WindowsProcessKiller pKiller = new WindowsProcessKiller();

		// To kill a process by ImageName/ProcessName
		String processName = "javaw.exe";
		boolean isRunning = pKiller.isProcessRunning(processName);

		System.out.println("is " + processName + " running : " + isRunning);

		if (isRunning) {
			pKiller.killProcess(processName);
		} else {
			System.out.println("Not able to find the process : " + processName);
		}

	}

}