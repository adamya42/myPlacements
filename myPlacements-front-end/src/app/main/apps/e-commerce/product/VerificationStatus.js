import React from "react";
import clsx from "clsx";
import _ from "@lodash";

export const Statuses = [
  {
    id: 1,
    name: "Verified",
    color: "bg-green text-white",
  },
  {
    id: 2,
    name: "Not Verified",
    color: "bg-red-dark text-white",
  },
];

function VerificationStatus(props) {
  return (
    <div
      className={clsx(
        "inline text-12 p-4 rounded truncate",
        _.find(Statuses, { name: props.name }).color
      )}
    >
      {props.name}
    </div>
  );
}

export default VerificationStatus;
