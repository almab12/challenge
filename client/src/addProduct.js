import React, { Component } from 'react';
class AddProduct extends Component {
  state =
    {
      id: 0,
      name: '',
      price: 0,
      description: ''

    }
  handleChange = (e) => {
    this.setState({
      [e.target.id]: e.target.value
    })
  }
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.addProduct(this.state);
    this.setState({
      id: 0,
      name: '',
      price: 0,
      description: ''
    })
  }
  render() {
    return (
      <div className="card">
        <div className="card-header">
          Add a new product
        </div>
        <div className="card-body">
          <form className="form-inline" onSubmit={ this.handleSubmit }>
            <div className="form-group">
              <label>Name</label>
              <input type="text" className="form-control ml-sm-2 mr-sm-4 my-2" id="name" onChange={this.handleChange} value={this.state.name} required />
            </div>
            <div className="form-group">
              <label>Price</label>
              <input type="number" className="form-control ml-sm-2 mr-sm-4 my-2" id="price" onChange={this.handleChange} value={this.state.price} required />
            </div>
            <div className="form-group">
              <label>Description</label>
              <input type="text" className="form-control ml-sm-2 mr-sm-4 my-2" id="description" onChange={this.handleChange} value={this.state.description} required />
            </div>
            <div className="ml-auto text-right">
              <button type="submit" className="btn btn-dark my-2">Add</button>

            </div>
          </form>
        </div>
      </div>
    );
  }
}

export default AddProduct;
