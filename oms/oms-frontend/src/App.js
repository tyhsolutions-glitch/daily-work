import React, { useState } from "react";
import axios from "axios";

function App() {
  const [item, setItem] = useState("");
  const [price, setPrice] = useState("");
  const [response, setResponse] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      orderLines: [
        {
          item: item,
          price: price
        }
      ]
    };

    try {
      const res = await axios.post("http://localhost:8080/order", data);
      setResponse(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Order-API App</h1>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter item name"
          value={item}
          onChange={(e) => setItem(e.target.value)}
        />
        <br /><br />

        <input
          type="number"
          placeholder="Enter price"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
        />
        <br /><br />

        <button type="submit">Submit</button>
      </form>

      {response && (
        <div style={{marginTop:"20px"}}>
          <h3>✅Order Created</h3>
          <p><b>Order ID:</b>{response}</p>
        </div>
      )}
    </div>
  );
}

export default App;
