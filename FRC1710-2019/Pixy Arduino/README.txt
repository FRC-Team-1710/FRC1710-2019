



**README.txt**

Instructions on set up + documentation of available methods.

1. Import the 'arduino.jar' file into your buildpath as an external jar. (instructions here: https://www.youtube.com/watch?v=UtzAf8tyuAM) (side note: the video does not belong to me)

2. In your class, insert the statement import arduino.*; before your class definition.

3. Now, you can create an object of Arduino class which will allow you to call serialWrite() and serialRead().

4.Constructors of Arduino class:
	1. public Arduino() - empty constructor to be used only if port is unknown and it is imperative to initialise object. If this constructor is called, then calling setPortDescription(String portDescription) is essential to set the serial port.
	2. public Arduino(String portDescription) - parameterised constructor that initialises the arduino object and sets the communication port to that which has been specified.
	3. public Arduino(String portDescription, int baud_rate) - parameterised constructor that initialises the arduino object and sets the communication port to that which has been specified. it also sets the baud rate for the serial communication. this is the recommended constructor

5. Methods in Arduino class:
	1. boolean openConnection() - opens the connection if portDescription has been initialised. returns a boolen depending on whether the connection was successful. also displays an error message to the user when connection was unsuccessful. make sure to call this before anything else or exceptions will be thrown
	2. void closeConnection() - closes connection to serial port. be sure to call this at the end otherwise the port will remain occupied
	3. void setPortDescription(String portDescription) - setter method to change serial port to which the object is attached.
	4. void setBaudRate(int baud_rate) - sets the baud rate for serial comm.
	5. SerialPort getSerialPort() - returns an object of type SerialPort with the current SerialPort
	6. String getPortDescription() - getter method returning the String containng the port description.
	7. String serialRead() - runs until there is no more data available in the serial to be read. then returns all of the data as a string. this may be an infinite loop depending on availability of data
	8. String serialRead(int limit) - returns a string containing as many readings as the value of limit. recommended for reading
	9. void serialWrite(String s) - writes the contents of the entire string to the serial at once. written as string to serial.
	10. void serialWrite(String s, int noOfChars, int delay) - writes the contents of the strings to the serial gradually. It writes the string in incremental steps with 'noOfChars' charaacters each time, with a pause of 'delay' milliseconds between each write. written as string to serial. recommended to write String
	11. void serialWrite(char c) - writes the individual char to the serial in datatype char.
	12. void serialWrite(char c, int delay) - writes the individual char to the serial in datatype char and pauses the thread for delay milliseconds after. recommended to write char

6. PortDropdownMenu class: it extends the JComboBox class (from java.swing) and contains a method called refreshMenu() which populates the dropdown menu with the currently available serial ports. For all practical purposes, it is an object of the JComboBox class, with the addition refreshMenu() method.
