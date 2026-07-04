// Initialize Stripe.js
const stripe = Stripe('pk_test_51SOMvLLqe3opEh725iFjyNe0v5R99bwJbYMqWgj376uYt2JiQzvYsrnh76pIaeEClEBTt1JEHnEmBOEFwbiWNC3400tlsfJQWC');

initialize();

// Fetch Checkout Session and retrieve the client secret
async function initialize() {
  const fetchClientSecret = async () => {
    const response = await fetch("/create-checkout-session", {
      method: "POST",
    });
    const { clientSecret } = await response.json();
    return clientSecret;
  };

  // Initialize Checkout
  const checkout = await stripe.initEmbeddedCheckout({
    fetchClientSecret,
  });

  // Mount Checkout
  checkout.mount('#checkout');
}