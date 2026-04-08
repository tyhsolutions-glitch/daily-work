import '@testing-library/jest-dom';  // This should work for most setups
global.alert = jest.fn();  // Mocking window.alert
