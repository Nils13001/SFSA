
<h1> Secure File Storage and Access System</h1>

<h3>Features</h3>
<ul type="square">
<li>ReactJS + Spring-Boot Project with Maven and TailwindCSS
<li>AWS services used include S3, IAM and Secrets Manager
<li>Hybrid Cryptography Employing AES(Fernet), and RSA
</ul>


<h3>Project Structure</h3>
<ul type="square">
<li><b>Secure-File-Storage:</b> Backend code using Spring Boot.
<li><b>secure-file-storage-ui:</b> Frontend code using ReactJS + TailwindCSS
</ul>


<h3> Pre-requisites </h3>
<p align="justify"> <b> 1. IDE and Software Requirements: </b> Install VSCode for React and Java. However, can use IntelliJ too for Java. After cloning the repository to desired folder. Go inside the frontend folder and type<br><code>npm install</code>.<br>It will install all the modules needed.<br>
For backend, load the Java project and give maven build.

<p align="justify"> <b> 2. Setting-up AWS Account: </b> First of all, <a href= "https://docs.aws.amazon.com/accounts/latest/reference/manage-acct-creating.html"> create an account </a> in AWS as root user.
After that, <a href = "https://docs.aws.amazon.com/IAM/latest/UserGuide/id_users_create.html"> create an IAM user </a> as it is always advisable to experiment using IAM account rather than Root Account.
Now as a root user, <a href = "https://docs.aws.amazon.com/AmazonS3/latest/userguide/creating-bucket.html"> create a bucket </a> for storing up your files and 
provide <a href = "https://docs.aws.amazon.com/AmazonS3/latest/userguide/example-walkthroughs-managing-access-example1.html"> permissions to IAM users</a>. 
Add necessary credentials/secrets to AWS Secrets Manager.

<p align="justify"> <b> 3. Project run: </b> First, run the backend code on port 8080 and once it is running, give <code>npm start</code> command in frontend folder; it will start Frontend on port 3000

<h3> Output Screenshots </h3>

<h4> Home Page<br><br>
<img src="https://github.com/user-attachments/assets/814c0515-ef6e-409f-95a1-d481b7f8b7c5">
</h4>

<h4> Listing and Downloading of File<br><br>
<img src="https://github.com/user-attachments/assets/8b801c1e-e000-429d-9467-5b92380a9d55"
</h4>

<h4> Encryption and Uploading of File<br><br>
<img src="https://github.com/user-attachments/assets/447d02cc-f264-4630-b15a-1cd4dc8a992b">
</h4>

<h4> File on Cloud<br><br>
<img src="https://github.com/user-attachments/assets/c22b1b5a-7d53-4530-8647-ad2a88c6ad54">
</h4>

<h4> Gmail Intimation<br><br>
<img src="https://github.com/user-attachments/assets/086b1403-43bb-414e-8e63-c4e67e856110">
</h4>

<h4> Decryption of File<br><br>
<img src="https://github.com/user-attachments/assets/4185f32d-1c37-4747-859d-b34c4d075e3a">
</h4>
