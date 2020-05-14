import React from "react";
import { Icon, Typography } from "@material-ui/core";
import { FuseAnimate } from "@fuse";

function MultiUserHeader() {
  return (
    <div className="flex flex-1 items-center justify-between p-8 sm:p-24 relative">
      <div className="flex flex-shrink items-center sm:w-224">
        <div className="flex items-center">
          <FuseAnimate animation="transition.expandIn" delay={300}>
            <Icon className="text-32 mr-12">account_box</Icon>
          </FuseAnimate>
          <FuseAnimate animation="transition.slideLeftIn" delay={300}>
            <Typography variant="h6" className="hidden sm:flex">
              Multiple Users
            </Typography>
          </FuseAnimate>
        </div>
      </div>
    </div>
  );
}

export default MultiUserHeader;
