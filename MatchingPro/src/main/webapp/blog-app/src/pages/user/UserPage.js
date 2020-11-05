import React from 'react';

const UserPage = () => {

	useEffect(() => {
		fetch("http://localhost:8000/user/myTeam", {
			method: "get",
			headers: {
				'Authorization': localStorage.getItem("Authorization")
			}
		}).then((res) => {
			console.log("MyTeamForm:: login한 ID의 Team Id display res", res);
			return res.text();
		}).then((res) => {
			console.log("MyTeamForm:: login한 ID의 Team Id display", res);

			fetch(`http://localhost:8000/teamDetail/${res}`, {
				// 여기 들어가는 res는 현재 로그인 한 ID의 TeamID // 팀 정보 가져와서 소속 선수, 팀장 display
				method: "get",
			}).then((res) => {
				return res.json();
			}).then((res) => {
				console.log("MyTeam:: Team info fetch display res: ", res);
				setTeam(res);
				setOwner(res.owner);
				setUsers(res.users);
			});

			fetch(`http://localhost:8000/user/teamParty/${res}`, {
				// 여기 들어가는 res는 현재 로그인 한 ID의 TeamID // 팀 가입 요청 가져와서 display
				method: "get",
				headers: {
					'Authorization': localStorage.getItem("Authorization")
				}
			}).then((res) => {
				return res.json();
			}).then((res) => {
				console.log("MyTeam:: party list(from team) info fetch display res: ", res);
				setPartys(res);
			});

			// team battle fetch
			fetch(`http://localhost:8000/user/loginBattleList`, {
				// 여기 들어가는 res는 현재 로그인 한 ID의 TeamID // 팀 가입 요청 가져와서 display
				method: "get",
				headers: {
					'Authorization': localStorage.getItem("Authorization")
				}
			}).then((res) => {
				console.log("MyTeam:: battle1 info fetch display res: ", res);
				//const tmp = null;
				return res.json();
			}).then((res) => {
				console.log("MyTeam:: battle2 info fetch display res: ", res);
				setBattles(res);
			});

		});
	}, []);
	
	return (
		<div>
			
		</div>
	);
};

export default UserPage;