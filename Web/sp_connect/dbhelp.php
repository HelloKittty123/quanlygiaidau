<?php 
require_once ('config.php');

/**
 * Thực hiện truy vấn
 */

function excute($sql) {
	//create connection to database
	$conn = mysqli_connect(HOST, USERNAME, PASSWORD, DATABASE);

	//query
	mysqli_query($conn, $sql);

	//Close connection
	mysqli_close($conn);
}

/**
 * Trả về kết quả
 */ 
function excuteResult($sql) {
	//create connection to database
	$conn = mysqli_connect(HOST, USERNAME, PASSWORD, DATABASE);

	//query
	$resultset = mysqli_query($conn, $sql);
	$list = [];
	if($resultset != null) {
		while($row = mysqli_fetch_array($resultset, 1)) {
			$list[] = $row;
		}
	}

	//Close connection
	mysqli_close($conn);

	return $list;
}

?>