import React, { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [item, setItem] = useState("");
  const [price, setPrice] = useState("");
  const [quantity, setQuantity] = useState(1);
  const [address, setAddress] = useState({ street: "", city: "", zip: "" });
  const [orderLines, setOrderLines] = useState([]);
  const [response, setResponse] = useState(null);

  const handleAddItem = () => {
    if (item && price > 0 && quantity > 0) {
      setOrderLines([...orderLines, { item, price, quantity }]);
      setItem(""); setPrice(""); setQuantity(1);
    } else alert("Please fill valid item, price, and quantity.");
  };

  const handleDeleteItem = (index) => setOrderLines(orderLines.filter((_, i) => i !== index));

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { orderLines, address };
    try {
      const res = await axios.post("http://localhost:8080/order", data);
      setResponse(res.data);
    } catch (err) { console.error(err); }
  };

  return (
    <div className="app-container">
      <h1>Order-API App</h1>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <input type="text" placeholder="Item" value={item} onChange={e => setItem(e.target.value)} />
          <input type="number" placeholder="Price" value={price} onChange={e => setPrice(e.target.value)} />
          <input type="number" placeholder="Quantity" value={quantity} onChange={e => setQuantity(e.target.value)} />
          <button type="button" className="btn" onClick={handleAddItem}>Add to List</button>
        </div>
        <div className="address-group">
          <input type="text" placeholder="Street" value={address.street} onChange={e => setAddress({ ...address, street: e.target.value })} />
          <input type="text" placeholder="City" value={address.city} onChange={e => setAddress({ ...address, city: e.target.value })} />
          <input type="text" placeholder="Zip" value={address.zip} onChange={e => setAddress({ ...address, zip: e.target.value })} />
        </div>
        <button type="submit" className="btn-submit">Submit Order</button>
      </form>

      <div className="order-list">
        <h3>Order Items:</h3>
        <ul>
          {orderLines.map((line, index) => (
            <li key={index}>
              {line.quantity} x {line.item} - ₹{line.price}
              <button onClick={() => handleDeleteItem(index)} className="btn-delete">Delete</button>
            </li>
          ))}
        </ul>
      </div>

      {response && <div className="response"><h3>✅ Order Created</h3><p><b>Order ID:</b> {response}</p></div>}
    </div>
  );
}

export default App;
