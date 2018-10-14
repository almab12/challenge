import React, { Component } from 'react';
class SearchProduct extends Component {
  state =
    {
      id: 0,
      name: '',
      price: 0,
      catagory: ''

    }
  handleChange = (e) => {
    this.setState({
      [e.target.id]: e.target.value
    })
  }
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.searchProduct(this.state);
    this.setState({
      id: 0,
      name: '',
      price: 0,
      catagory: ''
    })
  }
  render() {
    return (
      <div className="card">
        <div className="card-header">
        Search products
        </div>
        <div className="card-body">
          <form className="form-inline" onSubmit={ this.handleSubmit }>
            <div className="form-group">
              <label>Name</label>
              <input type="text" className="form-control ml-sm-2 mr-sm-4 my-2" id="name" onChange={this.handleChange} value={this.state.name} required />
            </div>
            <div className="ml-auto text-right">
              <button type="submit" className="btn btn-dark my-2">Search</button>

            </div>
          </form>
        </div>
      </div>
    );
  }
}

export default SearchProduct;