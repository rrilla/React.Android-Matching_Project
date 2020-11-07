import React, { useEffect, useState } from 'react';
import { Jumbotron, Container } from 'react-bootstrap';
import styled from 'styled-components';

const MainCardStyle = styled.div`
  width: 100%;
  margin: auto;
`;

const SlideStyle = styled.div`
	margin-top:15%;
	margin-bottom:4%;
`;
const Mypage = () => {

	const [user, setUser] = useState([]);
	const [partys, setPartys] = useState([]);

	useEffect(() => {
		fetch("http://localhost:8000/user/loginid", {
			method: "get",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => {
			console.log("rr", res);
			return res.text();
		}).then((res) => {
			console.log("Mypage useEffect, id of signin user::", res);



			fetch(`http://localhost:8000/userDetail/${res}`, {
				method: "get",
			}).then((res) => {
				return res.json();
			}).then((res) => {
				console.log("Mypage useEffect, res of signin user::", res);
				setUser(res);
			});
			// 여기 아이디로 파티 가져오는 패치 res가 id

			fetch(`http://localhost:8000/user/partyList/${res}`, {
				method: "post",
				headers: {
					'Authorization': localStorage.getItem("Authorization")
				}
			}).then((res) => {
				console.log("zzzz",res);
				return res.json();
			}).then((res) => {
				console.log("sssssssssssss", res);
			});

		});
	}, []);

	return (
		<Container>
			<SlideStyle>
				<MainCardStyle>
					<Jumbotron>
						지역 : {user.location} <br />
						포지션 : {user.position} <br />
						닉네임 : {user.nickname} <br />

					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container>
	);
};

export default Mypage;