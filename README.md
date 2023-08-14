<h1>Twitter Simulation Project</h1><p>This is a Java-based Twitter simulation project that replicates some of the core features of the popular social media platform Twitter. The project is implemented using Java for the server-side, JavaFX for the client-side GUI, SQLite for data management, JWT for user authentication, and TCP sockets for maintaining the connection between the server and the client. The project follows the Model-View-Controller (MVC) design pattern for organizing the codebase.</p><h2>Features</h2><ul><li><p><strong>Authentication</strong>: User authentication is implemented using JSON Web Tokens (JWT), ensuring secure access to the platform.</p></li><li><p><strong>Signup and Login</strong>: Users can create accounts by signing up with their credentials. Existing users can log in to their accounts.</p></li><li><p><strong>Timeline</strong>: The timeline displays tweets from followed users, including the ability to view original tweets, retweets, quotes, and replies.</p></li><li><p><strong>Tweet Actions</strong>: Users can create new tweets, retweet, quote, like, and reply to tweets posted by others.</p></li><li><p><strong>User Profiles</strong>: Each user has a profile page that showcases their information, including followers, followings, avatar, headline, bio, location, and website.</p></li><li><p><strong>Following and Blocking</strong>: Users can follow and unfollow each other. They can also block and unblock other users.</p></li><li><p><strong>Explorer</strong>: The explorer feature allows users to search for other users and hashtags within the platform.</p></li><li><p><strong>Direct Messages</strong>: Users can send and receive direct messages, enabling private conversations between users.</p></li></ul><h2>Technologies Used</h2><ul><li>Java for server-side logic and socket communication</li><li>JavaFX for the client-side graphical user interface</li><li>SQLite for local data storage and management</li><li>JSON Web Tokens (JWT) for user authentication</li><li>Model-View-Controller (MVC) design pattern for code organization</li></ul><h2>How to Run</h2><ol><li>Clone this repository to your local machine.</li><li>Open the server-side code in your preferred Java IDE.</li><li>Build and run the server-side code to start the server.</li><li>Open the client-side code in your preferred Java IDE.</li><li>Build and run the client-side code to launch the GUI.</li><li>Follow the on-screen instructions to sign up or log in and start using the Twitter simulation.</li></ol><h2>How to Run without java IDE</h2><ol><li><p>Clone this repository to your local machine:</p><pre><div class="bg-black rounded-md mb-4"><div class="flex items-center relative text-gray-200 bg-gray-800 px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span></span><button class="flex ml-auto gap-2"><svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">git <span class="hljs-built_in">clone</span> https://github.com/your-username/twitter-simulation.git
</code></div></div></pre></li><li><p><strong>Run the Server</strong>:</p><ul><li>Open a terminal and navigate to the server-side directory:<pre><div class="bg-black rounded-md mb-4"><div class="flex items-center relative text-gray-200 bg-gray-800 px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span></span><button class="flex ml-auto gap-2"><svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash"><span class="hljs-built_in">cd</span> src/main/java/com/example/twitter_client/Server
</code></div></div></pre></li><li>Compile and run the server code:<pre><div class="bg-black rounded-md mb-4"><div class="flex items-center relative text-gray-200 bg-gray-800 px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span></span><button class="flex ml-auto gap-2"><svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">javac Main.java
java Main
</code></div></div></pre></li></ul></li><li><p><strong>Run the Client</strong>:</p><ul><li>Open another terminal window and navigate to the client-side directory:<pre><div class="bg-black rounded-md mb-4"><div class="flex items-center relative text-gray-200 bg-gray-800 px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span></span><button class="flex ml-auto gap-2"><svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash"><span class="hljs-built_in">cd</span> src/main/java/com/example/twitter_client
</code></div></div></pre></li><li>Compile and run the client code:<pre><div class="bg-black rounded-md mb-4"><div class="flex items-center relative text-gray-200 bg-gray-800 px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span></span><button class="flex ml-auto gap-2"><svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">javac Main.java
java Main
</code></div></div></pre></li></ul></li><li><p>Follow the on-screen instructions to sign up or log in and start using the Twitter simulation.</p></li></ol><p><strong>Note</strong>: Make sure you have Java and Git installed on your system before running the commands.</p><h2>Screenshots</h2><p>
 <p align="center">
  <img src="https://github.com/nedaZarei/Twitter-simulation/blob/master/assets/login.png" width="350" title="login">
</p>
 <p align="center">
  <img src="https://github.com/nedaZarei/Twitter-simulation/blob/master/assets/timeline.png" width="350" title="timeline">
   <img src="https://github.com/nedaZarei/Twitter-simulation/blob/master/assets/profile.png" width="350" title="profile">
</p>

</p><h2>Contributing</h2><p>Contributions to this project are welcome. Feel free to open issues and pull requests for bug fixes, improvements, or new features</p><h2>Acknowledgements</h2><p>The external libraries used in the project are AnimateFX, jdbc for sqlite, auth0/java-jwt for jwt authentication, </p><hr><p>By Calbon :)</p>
