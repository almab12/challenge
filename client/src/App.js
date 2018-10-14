import React, { Component } from 'react';
import Products from './Products';
import 'whatwg-fetch';
import AddProduct from './addProduct';
import SearchProduct from './searchProduct';
import Pagination from "react-js-pagination";

class App extends Component {
  constructor(){
    super();
    this.state = {
      products : [],
      activePage: 0,
      size:0,
      totalElements:0
    }
  }
  
  componentDidMount()
  {
    this.loadProductsFromServer(0,10);
  }

  addProduct = (product) => {

    fetch(`http://localhost:8080/products`, { method: 'POST' , 
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify( product )
    
    })

    product.id = Math.random();
    let products = [...this.state.products, product];
    this.setState({
      products : products
    })
  }
  deleteProduct = (id) => {
    fetch(`http://localhost:8080/products/${id}`, { method: 'DELETE' })
    let products = this.state.products.filter( product => {
      return product.id !== id;
    });
    this.setState({
      products : products
    })
  }
  updateProduct = (product, index) => {
    const state = [...this.state.products];
    let currentProduct = {...state[index]};
    currentProduct = product;
    state[index] = currentProduct;
    this.setState({
      products: state
    });
    fetch(`http://localhost:8080/products/`+product.id, { method: 'POST' , 
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify( product )
    
    })
  }

  loadProductsFromServer = (page, limit) => {
    fetch('http://localhost:8080/products/?limit='+limit+'&page='+page)
      .then(data => data.json())
      .then((res) => {
        
        this.setState({ products: res.content, activePage: res.number, size: res.size, totalElements: res.totalElements });
      });
  }

  searchProduct = (search) => {
    fetch('http://localhost:8080/searchproducts?search='+search.name)
    .then(data => data.json())
      .then((res) => {
        this.setState({ products: res.content });
      });
  }

  handlePageChange(pageNumber) {
    this.loadProductsFromServer(pageNumber, 10);
    console.log(`active page is ${pageNumber}`);
    this.setState({activePage: pageNumber});
  }


  render() {
    return (
      <div className="crud-app">
        <header className="app-header">
          <h1 className="app-title">React Crud App</h1>
        </header>
        <div className="container pt-5">
          <AddProduct addProduct={this.addProduct}/>
          <SearchProduct searchProduct={this.searchProduct}/>
          <Products products={this.state.products} deleteProduct={ this.deleteProduct } updateProduct={ this.updateProduct} />
          <Pagination
          activePage={this.state.activePage}
          itemsCountPerPage={this.state.size}
          totalItemsCount={this.state.totalElements}
          pageRangeDisplayed={5}
          onChange={this.handlePageChange.bind(this)}
        />
        </div>
      </div>
    );
  }
}

export default App;
