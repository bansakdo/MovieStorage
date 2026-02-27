import { useState, useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Layout from './components/Layout';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import VideoListPage from './pages/VideoListPage';
import UserListPage from './pages/UserListPage';
import SearchResultPage from './pages/SearchResultPage';
import api from './api/client';

export default function App() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    api.get('/auth/me')
      .then((res) => {
        if (res.data.authenticated) {
          setUser({ username: res.data.username });
        }
      })
      .catch(() => setUser(null));
  }, []);

  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout user={user} setUser={setUser} />}>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage setUser={setUser} />} />
          <Route path="/videos" element={<VideoListPage />} />
          <Route path="/users" element={<UserListPage />} />
          <Route path="/search" element={<SearchResultPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

