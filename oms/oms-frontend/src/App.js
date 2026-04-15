import React, { useState } from "react";
import axios from "axios";

function App() {
  const [item, setItem] = useState("");
  const [price, setPrice] = useState("");
  const [quantity, setQuantity] = useState(1);
  const [street, setStreet] = useState(""); 
  const [city, setCity] = useState(""); 
  const [zip, setZip] = useState(""); 
  const [orderLines, setOrderLines] = useState([]); 
  const [response, setResponse] = useState(null);

  const handleAddItem = () => {
    setOrderLines([...orderLines, { item, price, quantity }]);
    setItem("");
    setPrice("");
    setQuantity(1);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = {
      orderLines: orderLines, 
      address: {
        street: street,  
        city: city,
        zip: zip
      }
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

        <input
          type="number"
          placeholder="Enter quantity"
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
        />
        <br /><br />

        <button type="button" onClick={handleAddItem}>Add to List</button>
        <br /><br />

        <input
          type="text"
          placeholder="Enter street"
          value={street}
          onChange={(e) => setStreet(e.target.value)}
        />
        <br /><br />

        <input
          type="text"
          placeholder="Enter city"
          value={city}
          onChange={(e) => setCity(e.target.value)}
        />
        <br /><br />

        <input
          type="text"
          placeholder="Enter zip"
          value={zip}
          onChange={(e) => setZip(e.target.value)}
        />
        <br /><br />

        <button type="submit">Submit</button>
      </form>

      <h3>Order Lines:</h3>
      <ul>
        {orderLines.map((line, index) => (
          <li key={index}>
            {line.quantity} x {line.item} - ₹{line.price}
          </li>
        ))}
      </ul>

      {response && (
        <div style={{ marginTop: "20px" }}>
          <h3>✅ Order Created</h3>
          <p><b>Order ID:</b> {response}</p>
        </div>
      )}
    </div>
  );
}

export default App;
