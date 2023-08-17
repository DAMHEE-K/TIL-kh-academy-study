import { Route, Routes } from 'react-router-dom';
import './App.css';
import A from './components/A';
import B from './components/B';
import Layout from './layout/Layout';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="a" element={<A/>}/>
          <Route path="b" element={<B/>}/>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
