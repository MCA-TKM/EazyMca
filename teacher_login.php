<?php
$con = mysqli_connect('localhost','id7359724_eazyuser','eazypass','id7359724_eazydb');

if (mysqli_connect_errno())
 {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
 }
else{


	$user=$_POST['username'];
	$pass=$_POST['password'];

	$sql = "select teacherID from teacher_login where username='$user' and password='$pass';";
	$result = mysqli_query($con,$sql);
	$row=mysqli_fetch_array($result);
 	if($row)
 	{
 	   // $qry="select name,department,image from teacher_deatils where teacherID='row[0]';"
 	  //  $result = mysqli_query($con,$sql);
 		$arr = array('status' => 'true', 'teacherID' => $row[0]);
   		echo json_encode($arr);
	 }
	else
	{
		$arr = array('status' => 'false');
  		echo json_encode($arr);
	}
	mysqli_close($con);


}
?>