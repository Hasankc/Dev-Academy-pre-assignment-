import './App.css';
import axios from 'axios';
import React, { useState, useEffect } from 'react';
function App() {

  const [bikes, setBikes] = useState([])
  const [currentPage, setCurrentPage] = useState()
  const [totalPages, setTotalPages] = useState()

  const getallBikes = async (page) => {
    try {
      const res = await axios.get(`http://localhost:8080/allbikes?page=${page}`);
      console.log(res)
      setBikes(res.data.content);
      setCurrentPage(res.data.number)
      setTotalPages(res.data.totalPages)
    } catch (err) {
    }
  }

  useEffect(() => {
    console.log('useEffect')
    getallBikes(0)
    console.log('bikes', bikes)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (bikes.length) {
    return (
      <div >
        <div >
          <div >
            <table className="table">
              <thead>
                <tr>
                  <th>Departure</th>
                  <th>Return</th>
                  <th>Departure station id</th>
                  <th>Departure station name</th>
                  <th>Return station id</th>
                  <th>Return station name</th>
                  <th>Covered distance</th>
                  <th>Duration</th>
                </tr>
              </thead>
              <tbody>
                {bikes.map(bike => (
                  <tr>
                    <td > {bike.departureDate}</td>
                    <td> {bike.returnDate}</td>
                    <td> {bike.departureStationId}</td>
                    <td>{bike.departureStationName}</td>
                    <td> {bike.returnStationId}</td>
                    <td> {bike.returnStationName}</td>
                    <td> {bike.coveredDistance}</td>
                    <td> {bike.coveredDistance}</td>
                    <td> {bike.duration}</td>
                  </tr>
                ))}
              </tbody>
            </table>
            <nav aria-label="...">
              <ul className="pagination">
                <li className="page-item disabled">
                </li>
                {currentPage !== 0 && <li className="page-item"><a className="page-link" onClick={() => getallBikes(0)}>Go to the first page</a></li>}
                {currentPage >= 1 && <li className="page-item" aria-current="page"><a className="page-link" onClick={() => getallBikes(currentPage - 1)}>Go to the previous page - {currentPage}</a></li>}
                <li className="page-item active" aria-current="page"><span className="page-link">on page {currentPage + 1}</span></li>
                {currentPage < totalPages - 1 && <li className="page-item"><a className="page-link" onClick={() => getallBikes(currentPage + 1)}>Go to the next page - {currentPage + 2}</a></li>}
                {(currentPage + 1) !== totalPages && <li className="page-item"><a className="page-link" onClick={() => getallBikes(totalPages - 1)}>last page - {totalPages}</a></li>}
              </ul>
            </nav>
          </div>
        </div>
      </div>
    );
  } else {
    return (
      <div>Wait for bike</div>
    )
  }
}

export default App;