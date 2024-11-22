import time
import unittest

from selenium import webdriver
from selenium.webdriver.common.by import By


class GameCityTests(unittest.TestCase): # python web_app_tests.py
    def setUp(self):
        self.driver = webdriver.Chrome(
            executable_path="C:/Users/proko/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe")
        self.driver.get("http://localhost:8080")

    def test_elements_displayed(self):
        header = self.driver.find_element(By.TAG_NAME, "h1")
        self.assertTrue(header.is_displayed())

        city_input = self.driver.find_element(By.ID, "cityInput")
        self.assertTrue(city_input.is_displayed())

        start_btn = self.driver.find_element(By.ID, "startBtn")
        self.assertTrue(start_btn.is_displayed())

        submit_btn = self.driver.find_element(By.ID, "submitBtn")
        self.assertTrue(submit_btn.is_displayed())

        reset_btn = self.driver.find_element(By.ID, "resetBtn")
        self.assertTrue(reset_btn.is_displayed())

        log_area = self.driver.find_element(By.ID, "logArea")
        self.assertTrue(log_area.is_displayed())

    def test_process_city_success(self):
        driver = self.driver

        city_input = driver.find_element(By.ID, "cityInput")
        start_button = driver.find_element(By.ID, "startBtn")
        submit_button = driver.find_element(By.ID, "submitBtn")

        city_input.send_keys("Moscow")
        start_button.click()

        time.sleep(2)

        self.assertIn("Game started: Moscow", driver.page_source)

        city_input.clear()
        city_input.send_keys("Watford")
        submit_button.click()

        time.sleep(2)

        self.assertIn("City added: Watford.", driver.page_source)

    def test_process_city_starts_with_wrong_char(self):
        driver = self.driver

        city_input = driver.find_element(By.ID, "cityInput")
        start_button = driver.find_element(By.ID, "startBtn")
        submit_button = driver.find_element(By.ID, "submitBtn")

        city_input.send_keys("Moscow")
        start_button.click()

        time.sleep(2)

        self.assertIn("Game started: Moscow", driver.page_source)

        city_input.clear()
        city_input.send_keys("Dallas")
        submit_button.click()

        time.sleep(2)

        self.assertIn("City must starts with the last character of previous city!", driver.page_source)

    def test_process_city_does_not_exists(self):
        driver = self.driver

        city_input = driver.find_element(By.ID, "cityInput")
        start_button = driver.find_element(By.ID, "startBtn")
        submit_button = driver.find_element(By.ID, "submitBtn")

        city_input.send_keys("Moscow")
        start_button.click()

        time.sleep(2)

        self.assertIn("Game started: Moscow", driver.page_source)

        city_input.clear()
        city_input.send_keys("Watforddddddddd")
        submit_button.click()

        time.sleep(2)

        self.assertIn("City does not exists!", driver.page_source)

    def test_process_city_already_in_the_list(self):
        driver = self.driver

        city_input = driver.find_element(By.ID, "cityInput")
        start_button = driver.find_element(By.ID, "startBtn")
        submit_button = driver.find_element(By.ID, "submitBtn")

        city_input.send_keys("Moscow")
        start_button.click()

        time.sleep(2)

        self.assertIn("Game started: Moscow", driver.page_source)

        city_input.clear()
        city_input.send_keys("Moscow")
        submit_button.click()

        time.sleep(2)

        self.assertIn("This city is already in list!", driver.page_source)

    def test_process_city_timeout(self):
        driver = self.driver

        city_input = driver.find_element(By.ID, "cityInput")
        start_button = driver.find_element(By.ID, "startBtn")
        submit_button = driver.find_element(By.ID, "submitBtn")

        city_input.send_keys("Moscow")
        start_button.click()

        time.sleep(2)

        self.assertIn("Game started: Moscow", driver.page_source)

        time.sleep(30)

        city_input.clear()
        city_input.send_keys("Watford")
        submit_button.click()

        time.sleep(2)

        self.assertIn("Time limit exceeded! You lost. Game has been reset to first city.", driver.page_source)

    def tearDown(self):
        self.driver.quit()


if __name__ == "__main__":
    unittest.main()
