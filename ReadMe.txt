How to use:

Please run the following: 
javac Client.java ServerConnection.java
javac Server.java ClientHandler.java

then
java Server 
java Client

Run the "java Server" command in one window first. This widow is in charge of the Server. Then, run "java Client" in another window afterwards. This window is in charge of the client.
You can also open another window for another Client by calling java Client.

Commands for client:

login - this command is in charge of logging into a specific name, and this client will be recognized by that name unless if the command is called by the same user. By default, the user will be known as "unknown".
Ex: login Bryce
Bryce would be the name that would appear in everyone's windows.

say - this command is in charge of putting messages in the chat.
Ex: say Hello!
Bryce: Hello!
This will appear in every client's window.

list - this will list all of the members of the server. It will return all of the names (not including yours) of people currently in the server. 
Otherwise, it will say that no one is in the server.
Ex: list
List of people logged into the server: Ryan
This will appear only in the client's window.

join - this command will join the user into a specific group in the server. By default, everyone joins the 'general' group.
Ex: 
join math
Bryce joined into math group...
This message will only appear to those in the same group that client has joined in.

group - this command alone will list out the logged in members of the group.
Ex: 
group
List of people currently in the group: Ryan Bryce
This will only appear to the client who called the command.

bye or exit - both of these commands will exit the client out of the server.

Note: I'm sorry I don't have a Coordinator file. I didn't have enough time to make one. For now, I implemented a join command that imitates different servers.


