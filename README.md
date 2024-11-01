# Online Bookstore Order Management System

This project is an **Order Management System** for an online bookstore, designed to handle and process customer orders efficiently. The system includes data structures for managing orders, books, and customers, as well as search and sorting algorithms to facilitate easy tracking and organization of orders.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies](#technologies)
- [Data Structure and Algorithms](#data-structure-and-algorithms)
  - [Order Queue](#order-queue)
  - [Linear Search](#linear-search)
  - [Merge Sort](#merge-sort)
- [Installation](#installation)
- [Usage](#usage)
- [Example Files](#example-files)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

The Online Bookstore Order Management System is designed to:
- Store and manage information on books, customers, and orders.
- Process customer orders in a First-In-First-Out (FIFO) manner using a queue data structure.
- Search orders and books using a flexible linear search, allowing partial or relative matches.
- Sort books by various fields (title, author, price, etc.) using merge sort for easy tracking.

This system provides efficient order processing while ensuring that stock availability and order information are managed effectively.

## Features

- **Order Management**: Supports adding, processing, and tracking customer orders.
- **Inventory Check**: Validates stock availability for each order.
- **Data Persistence**: Saves processed orders to a CSV file for record-keeping.
- **Search Functionality**: Implements linear search to find books and orders by `title`, `author`, or `id`.
- **Sorting**: Allows sorting of books by title, author, or price in ascending or descending order using merge sort.

## Technologies

- **Java**: Core programming language for the project.
- **CSV**: Stores book, customer, and order data for easy data handling and persistence.

## Data Structure and Algorithms

### Order Queue

The order queue is implemented using a linked list-based structure, ensuring that the first order added is the first order processed. The queue operations include:
- **Enqueue**: Adds a new order to the end of the queue, verifying stock availability before adding.
- **Dequeue**: Removes and processes the oldest order, logging it in `output.csv`.

### Linear Search

A linear search algorithm is used to search through orders or books based on `title`, `author`, or `id`. This method is simple, efficient for the current dataset size, and compatible with unsorted data.

### Merge Sort

Merge sort is used to sort books based on specified fields (e.g., `title`, `author`, `price`). The algorithm supports sorting in ascending or descending order. This sorting mechanism allows for efficient organization and retrieval of book information.

## Installation

To get started with this project:
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/online-bookstore-order-management.git
