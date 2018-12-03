<?php
$con = mysqli_connect('localhost','id7359724_eazyuser','eazypass','id7359724_eazydb');


if (mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
else
{

	


	for ($x = 0; $x <= 40; $x++) {
    
		$sql="insert into table absent values(8,0,0,0,0,0,0,0);";
		$result = mysqli_query($con,$sql);

} 

//		$arr = array('status' => 'false');
  	//	echo json_encode($arr);
	
	mysqli_close($con);


}
?>