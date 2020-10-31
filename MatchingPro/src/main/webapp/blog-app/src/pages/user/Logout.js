import React from 'react';

const Logout = (props) => {

	const setToken = props.setToken;

	const logout = () => {
		localStorage.removeItem("Authorization");
		fetch(`http://localhost:8000/logout`, {
			method: "GET",
			headers: {
			}
		}).then(res => res.text())
			.then(res => {
				if (res === "ok") {
					setToken();
					alert("로그아웃에 성공하였습니다");
				} else {
					alert("로그아웃 실패");
				}
			});
	}
	return (
		<div>
			<button onClick={logout}>로그아웃</button>
		</div>
	);
};

export default Logout;