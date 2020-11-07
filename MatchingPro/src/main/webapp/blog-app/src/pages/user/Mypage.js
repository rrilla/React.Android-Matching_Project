import React, { useEffect, useState } from 'react';
import { Jumbotron, Container, Tooltip, Button, OverlayTrigger } from 'react-bootstrap';
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
		//fetch that brings loginid 
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
			//fetch to get the party information using the first login id 
				fetch("/user/partyList/{userid}", {
				method: "get",
			}).then((res) => {
				
				return res.json();
			}).then((res) => {
				console.log("partyfirstres"+res);
				console.log("Mypage useEffect, res of signin user::", res);
				setUser(res);
			});
			//
			// 여기 아이디로 파티 가져오는 패치 res가 id
			//ㄴㅐㄱ내가 ㅍㅐㅊ패ㄹ치ㄹ를 ㅁㅏㄴ만ㅡㄹ들ㅐ때 Jsonㅇㅔㅅ에서 set 

		});
	}, []);

	return (
		<Container>
			<SlideStyle>
				<MainCardStyle>
					

					<Jumbotron>
					<h2>
 				<OverlayTrigger
    				  key='top'
    				  placement='top'
    				  overlay={
     			   <Tooltip id={`tooltip-top`}>
      			   Location
      			  </Tooltip>
     				 }
    				>
     			 <Button variant="light">📍</Button>
    		</OverlayTrigger>
						 {user.location} </h2>
						<hr/>
						<h2>
 				<OverlayTrigger
    				  key='top'
    				  placement='top'
    				  overlay={
     			   <Tooltip id={`tooltip-top`}>
      			   포지션
      			  </Tooltip>
     				 }
    				>
     			 <Button variant="light">🏃</Button>
    		</OverlayTrigger>
						 {user.position}</h2> 
						<hr/>
					
						<h2>
 				<OverlayTrigger
    				  key='top'
    				  placement='top'
    				  overlay={
     			   <Tooltip id={`tooltip-top`}>
      			   닉네임
      			  </Tooltip>
     				 }
    				>
     			 <Button variant="light">📄</Button>
    		</OverlayTrigger>
						
						 {user.nickname}</h2>
						<hr/>

					
					</Jumbotron>
				</MainCardStyle>
			</SlideStyle>
		</Container>
	);
};

export default Mypage;