import React from "react";
import { Button, Icon } from "@material-ui/core";

function ContactUs() {
  return (
    <Button
      component="a"
      href="https://themeforest.net/item/fuse-react-react-redux-material-design-admin-template/21769397?ref=withinpixels"
      target="_blank"
      rel="noreferrer noopener"
      role="button"
      className="normal-case"
      variant="contained"
      color="secondary"
    >
      <Icon className="text-16 mr-4">mails</Icon>
      <span>Contact Us</span>
    </Button>
  );
}

export default ContactUs;
