# Volunteer Management System

A Java console application for managing volunteers, built to demonstrate core data structures implemented from scratch — without relying on Java's built-in Collections framework (no `ArrayList`, `LinkedList`, `Stack`, or `Queue` classes from `java.util`).

## Purpose

This project was built as part of my second-year coursework to apply data structures and algorithms concepts to a practical system. Rather than using Java's pre-built data structure classes, I implemented the underlying node-based structures manually to build a genuine understanding of how they work internally.

## Data Structures Implemented

- **Linked List** — used to store and manage the core collection of volunteers (add, search, remove, traverse)
- **Queue** (front/rear pointers) — used to manage volunteers waiting to be assigned to tasks, on a first-in-first-out basis
- **Stack** (top pointer) — used to manage a history/undo-style record of recent actions

All three structures share the same underlying `Node` class, linked manually via `next` pointers.

## Features

- Add a new volunteer
- Check if a volunteer already exists (by ID)
- Traverse and display all volunteers
- Queue volunteers for task assignment
- Stack-based tracking of recent operations

## Project Structure
