import { Outlet } from 'react-router-dom';
import NavBar from './NavBar';
import Footer from './Footer';

export default function Layout({ user, setUser }) {
  return (
    <div className="d-flex flex-column min-vh-100">
      <NavBar user={user} setUser={setUser} />
      <main className="flex-grow-1">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}

