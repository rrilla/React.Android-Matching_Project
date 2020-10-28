import React from 'react';
import { Route } from 'react-router-dom';
import LoginForm from './pages/user/LoginForm';
import Header from './components/Header';
import MainForm from './pages/MainForm';
import JoinForm from './pages/user/JoinForm';
import Team_create from './pages/team/Team_create';
import Logout from './pages/user/Logout';


const App = () => {
  return (
    <div>
      {/* <LoginForm></LoginForm> */}
      <Header></Header>
      {/* 로그인 페이지 */}
      <Route path="/" exact={true} component={MainForm}></Route>
      <Route path="/Login" exact={true} component={LoginForm}></Route>
      <Route path="/Join" exact={true} component={JoinForm}></Route>
      <Route path="/Team_create" exact={true} component={Team_create}></Route>
      <Route path="/Logout" exact={true} component={Logout}></Route>

    </div>
  );
};

export default App;