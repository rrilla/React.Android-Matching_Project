import React, { useEffect } from 'react';

const Team_schedule = (props) => {
	const id = props.match.params.id;

	useEffect(() => {
		// mainForm Teamcard data 
		fetch("http://localhost:8000/teamList", {
			method: "get",
		}).then((res) => {
			console.log("mainForm:: teamList rsponse", res);
			return res.json();
		}).then((res) => {
			console.log("mainForm:: teamList -> json data", res);
		});
	}, []);

	return (
		<div>
			{console.log("s", id)}
			<button></button>
		</div>
	);
};

export default Team_schedule;