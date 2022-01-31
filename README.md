# sn-web
//Prerequisites:

You need jdk1.8.0_201 to run this server.
If you don't have it,install it .



//Setting up the JAVA_HOME Variable in your system:

Once you have the JDK installation path:

Right-click the My Computer icon on your desktop and select Properties.
Click the Advanced tab.
Click the Environment Variables button.
Under System Variables, click New.
Enter the variable name as JAVA_HOME.
Enter the variable value (one of the paths mentioned above) as the installation path for the Java Development Kit.
Click OK.
Click Apply Changes.




//Setting the IP:

Open Command prompt
Type "ipconfig"
Find your IPv4 Address


Now run Notepad as Administrator
From Notepad, open the following file: c:\Windows\System32\Drivers\etc\hosts.
Go to the last line
Enter the IPv4 Address you found (space) "snakegame.com"
For eg: 192.168.1.5 snakegame.com



//Running the server:

Open the 'assignment-1-mug' folder
Click the Address bar
Select the path and type cmd
When the Command Prompt opens type "sbt run"
Wait till you see this line : (Server started, use Enter to stop and go back to the console...)
Now open your browser and type "http://snakegame.com:9000/join" on the search bar




//For the other users :

If anyone else wants to play the game
Give them the IPv4 address.

Ask them to do the following:
Now run Notepad as Administrator
From Notepad, open the following file: c:\Windows\System32\Drivers\etc\hosts.
Go to the last line
Enter the IPv4 Address (space) "snakegame.com"
For eg: 192.168.1.5 snakegame.com
Now open your browser and type "http://snakegame.com:9000/join" on the search bar

