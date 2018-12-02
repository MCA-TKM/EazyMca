<?php
$con = mysqli_connect('localhost','id7359724_eazyuser','eazypass','id7359724_eazydb');


if (mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
else
{

	$class=$_POST['class'];
	//gfdsghjsjd

	$sql = "select name from student_details where class='$class';";
	$result = mysqli_query($con,$sql);


	$rows = array();
	while($r = mysqli_fetch_assoc($result))
	{
	   
    	$rows[] = $r;
	}
	echo json_encode($rows);


	mysqli_close($con);


}
?>