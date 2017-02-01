How to build:
	On Mac/Linux:
		type 'make build'

How to build and run:
	On Mac/Linux:
		type 'make run'

Notes:
	The demo for the application spawns 3 GUI windows for testing.  They might spawn right on top of each other, so you may have to drag them off of one another before continuing.

---------------------------------------------------------------------------------------------------

Design Methodology:

My MessageBroadcast system is designed to be anonymous through simplicity.  The system is a socket based client-server model where N clients can connect to one central server. 

Server Design:

The server at its core is just two central data components, a message queue, and a public/private key pair.  The server works by storing broadcasts sent to it in the message queue, maintaining the message queue at or below a pre-determined size, and returning the message queue when it is requested.  If the message queue is full and a new request to broadcast a message comes in, the oldest message in the message queue is removed, and the new message is added.
When the server receives a request to broadcast a message, it must first decrypt the incoming message. The server decrypts the message using the private key that is only stored in memory. Once the message is decrypted, it is stored as the newest message in the message queue.  No other data about the message or the client is stored on the server.
The server serves the broadcast requests by responding for requests for the message queue contents from clients.  To return the message queue contents to the client, all messages, alongside their UUID identifiers, are written into a response and sent.

Client Design:

Clients use the MessageBroadcast API to communicate with the central server.  Clients can request the message queue from the server, or send a request to broadcast a message to the server.
When a client requests the message queue from the server, it gets each message in the message queue and the corresponding UUID for each message.  The client uses the UUID for the messages to determine which broadcasts they have already cached, and which broadcasts to cache now (because they are new since the last update).
Sending a request to broadcast a message to the MessageBroadcast server is a two step process.  First, the client requests the current public key from the server.  The server returns a 2048 bit RSA public key, which the client then uses to encrypt the message they wish to broadcast.  Once the message is encrypted, it is sent to the server for broadcast. 

GUI Design:

The GUI utilizes a MessageBroadcast client behind the scenes to keep track of all broadcasts received since the start of the session, and to send requests to broadcast to the server.  The GUI utilizes a scheduled background task in its own thread to get the message queue from the server every second, and render any new broadcasts onto the GUI.
	
	GUI Features:
		- Fully encrypted  (2048 bit RSA) messages of unlimited length
		- Load a broadcast from a text file.
		- Save broadcast history in GUI to file by clicking and dragging broadcast display area  to the desktop.

System Security:
- No personally identifying information is stored on, or even sent to the server.  The best way to ensure you do not compromise someones identity is to not know it in the first place.
- Only data on the server is the message queue (public), and the key pair.  Small amount of critical data is easier to secure.
- All messages are set to all lower case on the client side to hide any possibly identifying typing habits that users have.
- No time info is stored alongside the message queue so attackers cannot use timing attacks to determine who sent what.
Messages are encrypted (using 2048 bit keys) in transit so even if intercepted they cannot be read.
Private key stored in memory and is re-generated at the start of each session, so it is very difficult to obtain, making decrypting intercepted messages en route to the sever nearly impossible. 
The server does not track who is connected to the server, or how many people are connected to the server.

Possible Next Features:
- Random regeneration of key pair during server session.
- Sending picture messages.