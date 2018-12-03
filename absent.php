<?php
$con = mysqli_connect('localhost','id7359724_eazyuser','eazypass','id7359724_eazydb');


if (mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
else
{

	$absenties=$_POST['absenties'];


	$cals=$_POST['class']; 
	$subject=$_POST['subject'];


	$subject = str_replace('-', '_', $subject);
	$subject = str_replace(' ', '_', $subject);
	

	
	$int_no = array_map('intval', explode(',', $absenties));


	foreach ($int_no as $rollno) {
		

		$sql="update absent set $subject=$subject+1 where roll_no=$rollno";
		 		

		if ($con->query($sql) === TRUE) {
   			 echo "Record updated successfully";
		} else {
   			 echo "Error updating record: " . $con->error;
}


	}


//		$arr = array('status' => 'false');
  	//	echo json_encode($arr);
	
	mysqli_close($con);


}
?>