import React from 'react';
import './App.css';
import Navbar from './components/Navbar/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from './pages/Home/Home';
import Mentors from './pages/Mentors/Mentors';
import Mentees from './pages/Mentees/Mentees';
import Settings from './pages/Settings/Settings'
import AddMentor from './pages/AddMentor/AddMentor';

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>
          <Route path='/' exact component={Home} />
          <Route path='/mentors' component={Mentors} />
          <Route path='/mentees' component={Mentees} />
          <Route path='/add' component={AddMentor} />
          <Route path='/settings' component={Settings} />
        </Switch>
      </Router>
    </>
  );
}

export default App;