import { useEffect, useState } from 'react';
import { Container, Table, Spinner } from 'react-bootstrap';
import api from '../api/client';

const genderLabel = (gender) => {
  if (!gender) return '-';
  const g = typeof gender === 'string' ? gender : gender.gender;
  switch (g) {
    case 'MALE': return '남성';
    case 'FEMALE': return '여성';
    case 'NON_BINARY': return '논 바이너리';
    default: return g;
  }
};

export default function UserListPage() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get('/users')
      .then((res) => setUsers(res.data))
      .catch((err) => console.error('사용자 목록 조회 실패', err))
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return (
      <Container className="text-center mt-5">
        <Spinner animation="border" />
      </Container>
    );
  }

  return (
    <Container className="mt-4">
      <h1 className="mb-4">Movie Storage User List</h1>
      <Table striped bordered hover responsive>
        <thead>
          <tr>
            <th>사용자 ID</th>
            <th>이름</th>
            <th>나이</th>
            <th>성별</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td>{user.username}</td>
              <td>{user.name}</td>
              <td>{user.age}</td>
              <td>{genderLabel(user.gender)}</td>
            </tr>
          ))}
          {users.length === 0 && (
            <tr><td colSpan={4} className="text-center">등록된 사용자가 없습니다.</td></tr>
          )}
        </tbody>
      </Table>
    </Container>
  );
}

