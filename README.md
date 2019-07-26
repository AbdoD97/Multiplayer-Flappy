Full implementation of flappy birds game work with 2 players, ti displays the scores of other players somewhere on the screen, the game should terminate when one player fails and announce the winner.


The game starts automatically when 2 players are on the networks. (peer discovery)
Game information is transmitted using TCP


peer-discovery mechanism, that is, any node that enters a network will be able to find other nodes running the same software automatically, this is done using UDP broadcasting
Broadcasting allows the node to send packets to everybody in the network using a special IP address. Broadcasting is used in DHCP as the device that just entered the network doesn't know the IP of the router/switch to obtain IP from.


Broadcasting stop after a device is connected. You'll use broadcasting to share certain information by which other peers receiving that information will use it to establish a TCP connection with the device.
