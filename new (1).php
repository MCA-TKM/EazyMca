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
 	    $qry="select name,department,image from teacher_details where teacherID='$row[0]';";
 	    
 	    $result1 = mysqli_query($con,$qry);
 	 
 	    
 	    $row1=mysqli_fetch_array($result1);
        
 		$arr = array('status' => 'true', 'teacherID' => $row[0],'name' => $row1['name'],'department' =>$row1['department'],'image'=> $row1['image']);
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