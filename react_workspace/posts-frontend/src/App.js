import './App.css';
import { Route, Routes } from 'react-router-dom';
import Layout from './layout/Layout';
import Home from './routes/Home';
import PostList from './routes/PostList';
import PageNotFound from './routes/PageNotFound';
import PostDetail from './routes/PostDetail';
import PostCreate from './routes/PostCreate';
import PostUpdate from './routes/PostUpdate';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Layout/>}>
          <Route path='' element={<Home/>}/>
          <Route path='posts' element={<PostList/>}/>
          <Route path='posts/detail/:id' element={<PostDetail/>}/>
          <Route path='posts/create' element={<PostCreate/>}/>
          <Route path='posts/update/:id' element={<PostUpdate/>}/>
          <Route path='*' element={<PageNotFound/>}/>
        </Route>
      </Routes>
    </div>
  );
}

export default App;
