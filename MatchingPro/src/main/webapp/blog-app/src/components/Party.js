import React, { useEffect, useState } from 'react';
import { Button } from 'react-bootstrap';

const Party = (props) => {
	const id = props.id;
	const [party, setParty] = useState([]);
	const [user, setUser] = useState([]);
	const partyid = party.id;

	const joinTeamReq = () => {
		alert("요청 승인 됨");

		fetch(`http://localhost:8000/Acknowledgment/${partyid}`, {
			method: "PUT",
			headers: {
			}
		}).then(res => res.text())
			.then(res => {
				if (res === "ok") {
					alert("사용 가능한 팀 이름  입니다");
				} else {
					alert("중복 팀 이름  입니다");
				}
			});
	}


	useEffect(() => {
		fetch(`http://localhost:8000/user/partyInfo/${id}`, {
			method: "get",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => res.json())
			.then((res) => {
				setParty(res);
				setUser(res.user);
				console.log("Party:: fetch 받아온 party의 response", res);
			});
	}, []);


	return (
		<div>
			#{user.nickname} == <Button onClick={joinTeamReq} variant="outline-success">가입승인</Button>
		</div>
	);
};

export default Party;