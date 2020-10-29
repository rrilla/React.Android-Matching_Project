import React, { useState } from 'react';
import { Route } from 'react-router-dom';
import LoginForm from './pages/user/LoginForm';
import Header from './components/Header';
import MainForm from './pages/MainForm';
import JoinForm from './pages/user/JoinForm';
import Team_create from './pages/team/Team_create';
import Logout from './pages/user/Logout';
import LoginModal from './pages/user/LoginModal';
import Team_detail from './pages/team/Team_detail';

const App = () => {
  // 토큰이 있다(1) or 없다(0) => 있다=로그인o, 없다=로그인x
  const [isToken, setIsToken] = useState(0);

  // 로그인 하면 실행할거야. 0->1로 바꿔주는 함수. 
  const setToken = () => {
    if (isToken) {
      setIsToken(0);  // 1이면 0으로 바꾸고
    }
    else {
      setIsToken(1);         // 0이면 1로 바꾸고
    }
  }

  return (
    <div>
      {/* <LoginForm></LoginForm> */}
      <Header data={isToken} data2={setIsToken}></Header>
      {/* 로그인 페이지 */}
      <Route path="/" exact={true} component={MainForm}></Route>
      <Route path="/Login/:ss" exact={true} component={LoginForm}><LoginForm setToken={setToken}></LoginForm></Route>
      {/* <Route path="/Login/:ss" exact={true} component={LoginModal}><LoginForm setToken={setToken}></LoginForm></Route> */}
      <Route path="/Join" exact={true} component={JoinForm}></Route>
      <Route path="/Team_create" exact={true} component={Team_create}></Route>
      <Route path="/Logout" exact={true} component={Logout}><Logout setToken={setToken}></Logout></Route>
      <Route path="/Team_detail/:id" exact={true} component={Team_detail}></Route>
    </div>
  );
};

export default App;