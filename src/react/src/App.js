import logo from './logo.svg';
import './App.css';
import axios from "axios";
import {useEffect, useState} from "react";

function App() {

    /**/
    const [userList, setUserList] = useState(null);

    /* 로드 함수 */
    useEffect(() => {

      const url = "http://localhost:8089/user";
      const data = {

      }

      axios.get(
          url,
          data
      ).then(function(response) {
        console.log(response.data.userList);
        setUserList(response.data.userList);
        console.log("성공");
      }).catch(function(error) {
        console.log("실패");
      })
    },[]);


  const userListHeader = ['idx',
                          'id',
                          'password',
                          'birthday',
                          'name',
                          'gender',
                          'email',
                          'address',
                          'updateDate',
                          'addressDtl',
                          'phoneNum',
                          'rule',
                          'imageFileNo',
                          'createdDate',
                          'updateDate'];

  return (
    <div className="App">

      <table>
        <thead>
          <tr>
            <th>idx</th>
            <th>id</th>
            <th>password</th>
            <th>birthday</th>
            <th>name</th>
            <th>gender</th>
            <th>email</th>
            <th>address</th>
            <th>updateDate</th>
            <th>addressDtl</th>
            <th>phoneNum</th>
            <th>rule</th>
            <th>imageFileNo</th>
            <th>createdDate</th>
            <th>updateDate</th>
          </tr>
        </thead>
        <tbody>
        {
          userList
              ?
              userList.map((value, index) =>
                  <tr key={index}>
                    <td>{value.idx}</td>
                    <td>{value.id}</td>
                    <td>{value.password}</td>
                    <td>{value.birthday}</td>
                    <td>{value.name}</td>
                    <td>{value.gender}</td>
                    <td>{value.email}</td>
                    <td>{value.address}</td>
                    <td>{value.updateDate}</td>
                    <td>{value.addressDtl}</td>
                    <td>{value.phoneNum}</td>
                    <td>{value.rule}</td>
                    <td>{value.imageFileNo}</td>
                    <td>{value.createdDate}</td>
                    <td>{value.updateDate}</td>
                  </tr>
              )
              :
              (
                <tr>
                  <td colSpan="15">유저 정보가 없습니다.</td>
                </tr>
              )
        }
        </tbody>
      </table>
    </div>
  );
}

export default App;
