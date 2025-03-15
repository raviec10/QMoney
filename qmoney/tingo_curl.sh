# Replace 'YOUR_API_KEY' with your actual Tiingo API key
TIINGO_API_KEY='d6c585fdc09ce688edb1eb6eb610a13d99fbefb0'

# Fetch end-of-day data for GOOGL
curl -X GET "https://api.tiingo.com/tiingo/daily/GOOGL/prices" \
     -H "Content-Type: application/json" \
     -H "Authorization: Token ${TIINGO_API_KEY}"

# Fetch end-of-day data for AAPL
curl -X GET "https://api.tiingo.com/tiingo/daily/AAPL/prices" \
     -H "Content-Type: application/json" \
     -H "Authorization: Token ${TIINGO_API_KEY}"